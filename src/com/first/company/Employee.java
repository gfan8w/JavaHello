package com.first.company;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.Date;
import java.util.Objects;

public class Employee extends Person implements Externalizable, Cloneable {

	// 用于序列化时确定类的版本
	public static final long serialVersionUID = -232223442334234L;

	private transient int startYear; // transient 表示序列化时忽略

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return 9;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return Objects.hash(age, hireDate, name);

		// return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		/*if (!super.equals(obj)) {
			return false;
		}*/

		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return Objects.equals(name, other.name) && Objects.equals(hireDate, other.hireDate) && age == other.age;

	}

	protected Date hireDate;

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	// 自己约定序列化方式

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {

		out.writeUTF(name);
		out.writeInt(age);
		out.writeLong(hireDate.getTime());
		out.writeInt(startYear);
		out.writeObject(orientation);

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		age = in.readInt();
		hireDate = new Date(in.readLong());
		startYear = in.readInt();
		orientation = (Orientation) in.readObject();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(bOutputStream);
			outputStream.writeObject(this);
			outputStream.close();
			ByteArrayInputStream bin=new ByteArrayInputStream(bOutputStream.toByteArray());
			ObjectInputStream inputStream=new ObjectInputStream(bin);
			Object ret= inputStream.readObject();
			inputStream.close();
			return ret;
			
		} catch (Exception ex) {
			return null;
		}

	}

	private Orientation orientation;

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
