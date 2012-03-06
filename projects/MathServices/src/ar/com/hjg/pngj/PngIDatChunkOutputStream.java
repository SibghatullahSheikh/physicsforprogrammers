package ar.com.hjg.pngj;

import java.io.OutputStream;

import ar.com.hjg.pngj.chunks.ChunkHelper;
import ar.com.hjg.pngj.chunks.ChunkRaw;

/**
 * outputs the stream for IDAT chunk , fragmented at fixed size (16384 default).
 */
class PngIDatChunkOutputStream extends ProgressiveOutputStream {
	private static final int SIZE_DEFAULT = 16384;
	private final OutputStream outputStream;

	PngIDatChunkOutputStream(OutputStream outputStream) {
		this(outputStream, SIZE_DEFAULT);
	}

	PngIDatChunkOutputStream(OutputStream outputStream, int size) {
		super(size);
		this.outputStream = outputStream;
	}

	@Override
	public final void flushBuffer(byte[] b, int len) {
		ChunkRaw c = new ChunkRaw(len, ChunkHelper.IDAT, false);
		c.data = b;
		c.writeChunk(outputStream);
	}
}
