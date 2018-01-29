package common

class TxtHelper {

	/**
	 * 获得原始内容(skip bom)
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	private static char[] load(File file) throws IOException {
		// read text file, auto recognize bom marker or use
		// system default if markers not found.
		BufferedReader reader = null;
		CharArrayWriter writer = null;
		UnicodeReader r = new UnicodeReader(file.newInputStream(), null);

		char[] buffer = new char[16 * 1024];   // 16k buffer
		int read;
		try {
			reader = new BufferedReader(r);
			writer = new CharArrayWriter();
			while( (read = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, read);
			}
			writer.flush();
			return writer.toCharArray();
		} catch (IOException ex) {
			throw ex;
		} finally {
		   	try {
				writer.close();
				reader.close();
				r.close();
			} catch (Exception ex) {

		    }
		}
	}

	/**
	 * 文件重写，增加bom(write UTF8 BOM mark)
	 * @param f
	 * @param data 原始内容
	 * @throws java.io.IOException
	 */
	private static void rewrite(File f, char[] data) throws IOException {
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;

		def fos = f.newOutputStream();
		try {
		    // write UTF8 BOM mark
		    if (f.length() < 1) {
				byte[] bom = new byte[3];
				bom[0] = (byte)0xEF;
				bom[1] = (byte)0xBB;
				bom[2] = (byte)0xBF;
				fos.write(bom);
		    }

		    osw = new OutputStreamWriter(fos, "UTF-8");
		    bw = new BufferedWriter(osw);

			if (data != null) {
				bw.write(data);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
			   bw.close();
			   fos.close();
		    } catch (Exception ex) {

		    }
		}
	}

	/**
	 * bom重置
	 * @param file
	 */
	static void resetBom(File file) {
		char[] chars = this.load(file)//原始内容
		this.rewrite(file, chars)//文件重写（加入bom）
	}

	/**
	 * 按行获得文本内容(skip bom)
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	static List<String> readLines(File file) throws IOException {
		UnicodeReader r = new UnicodeReader(file.newInputStream(), null);
		BufferedReader reader = new BufferedReader(r);
		return reader.readLines()
	}

	static main(args) {
		def f = new File("E:/", "术语表.txt")

//		println com.ge.analyse.assist.TxtHelper.load(f)

		TxtHelper.resetBom(f)
	}

}
