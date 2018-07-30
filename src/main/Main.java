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
	//1.��ȡ�������ַ�ʽ��:  c1��c2��c3��
		//��һ�ַ�ʽ  
		 Class<?> c1=Class.forName("main.Student");
		 
		 //�ڶ��ַ�ʽ:java��ÿ�����Ͷ���class ����.
		 Class c2 = Student.class;
		 
		 //�����ַ�ʽ:java�������κ�һ��java������getClass ����
		 Student u = new Student();  
		 Class c3 = u.getClass(); //c3������ʱ�� (e������ʱ����User)  
		 
		 Class<?> c=c1;
	//2.�������󷽷�
		//������Class ��������ʾ�����һ����ʵ��  
		 Object o = c.newInstance(); //������User���޲������췽��.  
	//3.��ȡ�������
         //a.��ȡ�����������η���������
	         Field[] fs = c.getDeclaredFields(); 
	         //����ɱ䳤���ַ������洢����
	         StringBuilder sBuilder=new StringBuilder();
	         //ͨ��׷�ӵķ�������ÿ������ƴ�ӵ����ַ�����  
	         //����ߵ�public����  
	         sBuilder.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");
	         //��ߵ�ÿһ������  
	         for(Field field:fs){  
	        	 sBuilder.append("\t");//�ո�  
	        	 sBuilder.append(Modifier.toString(field.getModifiers())+" ");//������Ե����η�������public��static�ȵ�  
	        	 sBuilder.append(field.getType().getSimpleName() + " ");//���Ե����͵�����  
	        	 sBuilder.append(field.getName()+";\n");//���Ե�����+�س�  
	         }  
	         sBuilder.append("}");  
	         //��� ����
	         System.out.println(sBuilder);
         //b.��ȡ�ض����Բ���ֵ
	         //��ȡid����
	         Field fieldId=c.getDeclaredField("id");
	         Field fieldName=c.getDeclaredField("name");    
	         //ʵ��������ำ��temp  
	         Object temp = c.newInstance();
	         //���Ʒ�װ  
	         fieldId.setAccessible(true); //ʹ�÷�����ƿ��Դ��Ʒ�װ�ԣ�������java��������Բ���ȫ��  
	         fieldName.setAccessible(true); //ʹ�÷�����ƿ��Դ��Ʒ�װ�ԣ�������java��������Բ���ȫ��  
	         //set	��temp�����id���Ը�ֵ"110"  
	         fieldId.set(temp, 110);   
	         fieldName.set(temp, "Martin");   

	         //get  
	         System.out.println(fieldId.get(temp));  
	         System.out.println(fieldName.get(temp));
     //4.��ȡ��ķ���
         //���������η�,������,�����Ϣ,������Ϣ
	     Method method =c.getDeclaredMethod("setId",java.lang.Integer.class);
	     StringBuilder sBuilder2=new StringBuilder();
	     int modifiers=method.getModifiers();
	     String name=method.getName();
	     String paraType="( ";
	     Class<?>[] parameterTypes = method.getParameterTypes(); //��ò�������
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
