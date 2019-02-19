/*
 * Copyright (C) 2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iq80.leveldb.table;

import org.iq80.leveldb.util.Slice;
import org.iq80.leveldb.util.SliceInput;
import org.iq80.leveldb.util.SliceOutput;
import org.iq80.leveldb.util.Slices;

public class BlockTrailer {
	public static final int ENCODED_LENGTH = 5;

	private final int crc32c;

	public BlockTrailer(int crc32c) {
		this.crc32c = crc32c;
	}

	public int getCrc32c() {
		return crc32c;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		BlockTrailer that = (BlockTrailer) o;

		if (crc32c != that.crc32c) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return crc32c;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BlockTrailer");
		sb.append(", { crc32c=0x").append(Integer.toHexString(crc32c));
		sb.append('}');
		return sb.toString();
	}

	public static BlockTrailer readBlockTrailer(Slice slice) {
		SliceInput sliceInput = slice.input();
		int crc32c = sliceInput.readInt();
		return new BlockTrailer(crc32c);
	}

	public static Slice writeBlockTrailer(BlockTrailer blockTrailer) {
		Slice slice = Slices.allocate(ENCODED_LENGTH);
		writeBlockTrailer(blockTrailer, slice.output());
		return slice;
	}

	public static void writeBlockTrailer(BlockTrailer blockTrailer, SliceOutput sliceOutput) {
		sliceOutput.writeInt(blockTrailer.getCrc32c());
	}
}
