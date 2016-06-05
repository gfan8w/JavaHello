package com.first.stream;

import java.io.*;
import java.net.PasswordAuthentication;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirectoryManager;

import com.first.company.Employee;
import com.first.company.Orientation;

public class StreamTest {

	public static void main(String[] args) throws IOException{
		
		System.out.println(System.getProperty("user.dir"));
		
		FileInputStream inputStreamRaw=new FileInputStream("program.properties");
		
		
		int len=inputStreamRaw.available();
		byte[] b=new byte[len];
		
		inputStreamRaw.read(b);
		String string=new String(b);
		inputStreamRaw.close();
		//inputStream.reset();
		
		FileInputStream inputStream=new FileInputStream("program.properties");
		PushbackInputStream pInputStream;
		DataInputStream dataInputStream=new DataInputStream(pInputStream=new PushbackInputStream(new BufferedInputStream(inputStream)));
		
		//ZipEntry zipe=dataInputStream.getNextEntry();
		
		
		PrintWriter printWriter=new PrintWriter(System.out,true);
		printWriter.println(dataInputStream.readLine());
		
		//printWriter.flush();
		
		FileOutputStream f;
		PrintStream ps;
		printWriter.print(b);
		
		
		InputStreamReader inputStreamReader=new InputStreamReader(dataInputStream);
		BufferedReader fBufferedReader=new BufferedReader(inputStreamReader);
		
		fBufferedReader.mark(0);
		
		char[] cbuf=new char[len];
		inputStreamReader.read(cbuf);
		String cString=new String(cbuf);
		
		
		fBufferedReader.reset();
		
		
		inputStream.close();
		
		
		
		
		
		
		
		Charset sCharset=Charset.forName("gb2312");
		Set<String> aliases=sCharset.aliases();
		for(String s:aliases){
			System.out.println(s);
		}
		
		
		
		Map<String, Charset> dset=Charset.availableCharsets();
		for(String name :dset.keySet()){
			System.out.println(name);
		}
		
		String myName="ажлн";
		CharBuffer cBuffer=CharBuffer.wrap(new char[]{'a','b','c'});
		ByteBuffer bbfer=sCharset.encode(myName);
		byte[] bbyte=bbfer.array();
		
		//PrintWriter printWriter2=new PrintWriter(new FileOutputStream("lintao.txt"),true);
		//printWriter2.p
		
		DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream("lintao.txt"));
		dataOutputStream.write(bbyte);
		dataInputStream.close();
		
		
		ByteBuffer bbfu=ByteBuffer.wrap(bbyte);
		CharBuffer cBuffer2=sCharset.decode(bbfu);
		String string2=new String(cBuffer2.array());
		String string4=cBuffer2.toString();
		
		Charset engChar=Charset.forName("iso8859-1");
		CharBuffer cBuffer3=engChar.decode(bbfu);
		String string3=new String(cBuffer3.array());
	
		
		
		DataOutputStream dataOutputStream2=new DataOutputStream(new FileOutputStream(new File("employee.dat")));
		dataOutputStream2.writeInt(234);
		dataOutputStream2.writeDouble(434.67);
		dataOutputStream2.close();
		
		
		
		
		RandomAccessFile inouFile=new RandomAccessFile("employee.dat", "rw");
		inouFile.seek(4);
		double dra=inouFile.readInt();
		inouFile.seek(inouFile.length());
		inouFile.writeChar((int)'a');		

		
		
		inouFile.close();
		
		
		
		DataInputStream dataInputStream2=new DataInputStream(new BufferedInputStream(new FileInputStream(new File("employee.dat"))));
		int i24=dataInputStream2.readInt();
		dataInputStream2.readDouble();
		char achar=dataInputStream2.readChar();
		dataInputStream2.close();
		
		
		
		
		
		
		
		Employee e1=new Employee();
		e1.setAge(20);
		e1.setName("jim");
		e1.setHireDate(new GregorianCalendar(2014, Calendar.NOVEMBER, 13, 21, 45).getTime());
		e1.setOrientation(Orientation.HORIZATION);
		e1.setStartYear(3);
		
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("emp.dat"));
		objectOutputStream.writeObject(e1);
		objectOutputStream.close();
		
		ObjectInputStream objectInputStream =new ObjectInputStream(new FileInputStream("emp.dat"));
		Employee e2,e3;
		try {
			 e2=(Employee)objectInputStream.readObject();
			 e3=(Employee)e2.clone();
			 boolean iseq=e2==e3;
			 System.out.println("iseq "+iseq);
			 System.out.println(e2.equals(e3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
		Path path=Paths.get("hel", "wor","jet");
		path=path.resolve("hi.txt");
		
		
		
		Path path2=path.toAbsolutePath();
		Path root=path2.getRoot();
		Path path3=root.relativize(path2);
		
		File file=path3.toFile();
		
		
		Path temppath=path2;
		do{
			File f1=temppath.toFile();
			Path root1=temppath.getRoot();
			if( f1.isDirectory() && !f1.toPath().equals(root1) && !f1.exists()){
				f1.mkdir();
			}
			temppath=temppath.getParent();
		}while(!temppath.equals(root));
		
		
		path.getParent().toFile().mkdirs();
		
		
		BufferedReader inputStream2=Files.newBufferedReader(Paths.get("1.txt"));
		BufferedWriter bWriter=Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		
		String lines;
		do{
			lines=inputStream2.readLine();
			if(lines!=null)
				bWriter.write(lines+System.getProperty("line.separator"));
			
		}while(lines!=null);
		bWriter.close();
		inputStream2.close();
		
		
		
		Files.move(path, Paths.get(".",path.getFileName().toString()),StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);
		
		Files.deleteIfExists(path);
	
	
		Files.createDirectories(Paths.get("aa","bb","cc.txt").getParent());
		
		
		File nFile=new File(Paths.get(".",path.getFileName().toString()).toUri());
		BasicFileAttributes basicFileAttributes=Files.readAttributes(Paths.get(".",path.getFileName().toString()), BasicFileAttributes.class);
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar calendar=  new GregorianCalendar();
		calendar.setTimeInMillis(basicFileAttributes.lastModifiedTime().toMillis());
		
		System.out.println(simpleDateFormat.format(calendar.getTime()));
		
		
		
		try(DirectoryStream<Path> dStream=Files.newDirectoryStream(Paths.get("."),"*.txt")){
			dStream.forEach(item->{
				System.out.println(item.getFileName());
			});
			
		}
		
		
		
		
		
	}
	
	
	
	
}






























