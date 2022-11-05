public class Change {
public static void changeStrings(String x, String y){
x = "3";
y = "4";
}
public static void changeStudent(Student t){
t.nota=10;
}


public static void main(String[] args) {
String a = "1";
String b = "2";
changeStrings(a, b);
System.out.println("a="+a+" ,b="+b);
Student st=new Student(5);
changeStudent(st);
System.out.println("nota="+st.nota);
}
}

// C) a=1 , b=2 , nota =10;


//9) V-a afisa 8;
