public class Test1 {
int x;
static int y=0;
public static void modificaY() {
y+=10;
}
public void modificaY(int y) {
this.y+=y;
}
public void modificaX(int x) {
this.x=x+3;
}
public void functie() {
Test1 t=new Test1();
y=1;
t.y=2;
Test1.y=3;
Test1.modificaY();
t.modificaY(2);
System.out.println("y="+y);
x=1;
t.modificaX(2);
System.out.println("x="+x);
}
public static void main(String args[]) {
Test1 t=new Test1();
t.functie();
}
}


//y=15 , x=1;