package main;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class Main {

	public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException
	{
		//System.out.println("hello world!");
	//1.获取类有三种方式来:  c1；c2；c3。
		//第一种方式  
		 Class<?> c1=Class.forName("main.Student");
		 
		 //第二种方式:java中每个类型都有class 属性.
		 Class c2 = Student.class;
		 
		 //第三种方式:java语言中任何一个java对象都有getClass 方法
		 Student u = new Student();  
		 Class c3 = u.getClass(); //c3是运行时类 (e的运行时类是User)  
		 
		 Class<?> c=c1;
	//2.创建对象方法
		//创建此Class 对象所表示的类的一个新实例  
		 Object o = c.newInstance(); //调用了User的无参数构造方法.  
	//3.获取类的属性
         //a.获取所有属性修饰符、属性名
	         Field[] fs = c.getDeclaredFields(); 
	         //定义可变长的字符串来存储属性
	         StringBuilder sBuilder=new StringBuilder();
	         //通过追加的方法，将每个属性拼接到此字符串中  
	         //最外边的public定义  
	         sBuilder.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");
	         //里边的每一个属性  
	         for(Field field:fs){  
	        	 sBuilder.append("\t");//空格  
	        	 sBuilder.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等  
	        	 sBuilder.append(field.getType().getSimpleName() + " ");//属性的类型的名字  
	        	 sBuilder.append(field.getName()+";\n");//属性的名字+回车  
	         }  
	         sBuilder.append("}");  
	         //输出 属性
	         System.out.println(sBuilder);
         //b.获取特定属性并赋值
	         //获取id属性
	         Field fieldId=c.getDeclaredField("id");
	         Field fieldName=c.getDeclaredField("name");    
	         //实例化这个类赋给temp  
	         Object temp = c.newInstance();
	         //打破封装  
	         fieldId.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。  
	         fieldName.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。  
	         //set	给temp对象的id属性赋值"110"  
	         fieldId.set(temp, 110);   
	         fieldName.set(temp, "Martin");   

	         //get  
	         System.out.println(fieldId.get(temp));  
	         System.out.println(fieldName.get(temp));
     //4.获取类的方法
         //获得类的修饰符,方法名,入参信息,出参信息
	     Method method =c.getDeclaredMethod("setId",java.lang.Integer.class);
	     StringBuilder sBuilder2=new StringBuilder();
	     int modifiers=method.getModifiers();
	     String name=method.getName();
	     String paraType="( ";
	     Class<?>[] parameterTypes = method.getParameterTypes(); //获得参数类型
         Type[] genericParameterTypes = method.getGenericParameterTypes();
         for (int i = 0; i <= parameterTypes.length; i++) {
        	 if(i==0)
        		 paraType+=genericParameterTypes[i];
        	 else if(i==parameterTypes.length)
        	 {
        		 paraType+=");\n";
        		 break;
        	 }
        	 else
        		 paraType+=","+genericParameterTypes[i];
         } 
         sBuilder2.append(Modifier.toString(modifiers));
         sBuilder2.append(" "+name);
         sBuilder2.append(paraType);
         System.out.println("\n"+sBuilder2);
	}
}
