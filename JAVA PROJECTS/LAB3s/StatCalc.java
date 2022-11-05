public class StatCalc{
    public int nrelem;
    public double suma;
    public double suma_patrat;
    public double max = Double.NEGATIVE_INFINITY;
    public double min = Double.POSITIVE_INFINITY;
    public String elem="";
    public int count=0;
    
    
    //Metode de adaugare a numerelor;
    public void add(double nr)
    {
        if(nr!=0)
        {
        nrelem++;
        suma+=nr;
        suma_patrat+=nr*nr;
        if(nr<min)
        {
            min=nr;
        }
        if(nr>max)
        {
            max=nr;
        }
        if(count==7)
        {
            count=0;
            elem+="\n";
        }
        elem+=nr+",  ";
        count++;
        }
    }
    
    public int getCount()
    {
        return nrelem;
    }
    
    public double getSum()
    {
        return suma;
    }
    
    public double getMean()
    {
        return suma/nrelem;
    }
      
    public double getMin()
    {
        return min;
    }
    
    public double getMax()
    {
        return max;
    }
    
        public double getStandardDeviation()
    {
        double medie = getMean();
        return Math.sqrt(suma_patrat/nrelem - medie*medie);
    }
}
