package Problema1;

public class Cerc {
    private float raza;
    
    Cerc(float r){
    raza=r;
}
    
   public float getArie()
    {
        return (float)Math.PI*(raza*raza);
    }
   
   public float getCircumferinta()
   {
       return (float)Math.PI*raza*2;
   }
    
}
