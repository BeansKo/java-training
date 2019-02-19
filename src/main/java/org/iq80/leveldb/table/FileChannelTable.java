package org.iq80.leveldb.table;

import org.iq80.leveldb.util.Slice;
import org.iq80.leveldb.util.Slices;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Comparator;

public class FileChannelTable extends Table {
	public FileChannelTable(String name, FileChannel fileChannel, Comparator<Slice> comparator, boolean verifyChecksums) throws IOException {
		super(name, fileChannel, comparator, verifyChecksums);
	}

	@Override
	protected Footer init() throws IOException {
		long size = fileChannel.size();
		ByteBuffer footerData = read(size - Footer.ENCODED_LENGTH, Footer.ENCODED_LENGTH);
		return Footer.readFooter(Slices.copiedBuffer(footerData));
	}

	@Override
	protected Block readBlock(BlockHandle blockHandle) throws IOException {
		ByteBuffer uncompressedBuffer = read(blockHandle.getOffset(), blockHandle.getDataSize());
		Slice uncompressedData = Slices.copiedBuffer(uncompressedBuffer);
		return new Block(uncompressedData, comparator);
	}

	private ByteBuffer read(long offset, int length) throws IOException {
		ByteBuffer uncompressedBuffer = ByteBuffer.allocate(length);
		fileChannel.read(uncompressedBuffer, offset);
		if (uncompressedBuffer.hasRemaining()) {
			throw new IOException("Could not read all the data");
		}
		uncompressedBuffer.clear();
		return uncompressedBuffer;
	}
}
