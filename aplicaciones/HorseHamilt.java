package aplicaciones;
import librerias.estructurasDeDatos.grafos.*;


public class HorseHamilt{
    private GrafoDirigido   gf;
    private int           pos0;
    private int              x;
    private int              y;
    
    public HorseHamilt(int x,int y,int pos0){
        this.gf = new GrafoDirigido(((y-1)*10)+x); // Initialise graph with number of total vertex
        this.x = x;
        this.y = y;
        this.pos0 = pos0;
        this.generateGraph(x,y);
        System.out.println(this.gf);
    }
    
    private void generateGraph(int x,int y){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                this.makeMoves(i,j,x,y);
            }
        }
    }
    
    public void print(){
        System.out.println("Hay ciclo hamiltoniano: " + this.gf.hamiltCycle(this.pos0));
    }
    
    private void makeMoves(int i,int j,int x,int y){
        if(i+2 < x & j+1 < y){
            this.gf.insertarArista((j*10)+i,(((j+1)*10)+(i+2)),0);
            this.gf.insertarArista((((j+1)*10)+(i+2)),(j*10)+i,0);
        }
        if(i+2 < x & j-1 >= 0){
            this.gf.insertarArista((j*10)+i,(((j-1)*10)+(i+2)),0);
            this.gf.insertarArista((((j-1)*10)+(i+2)),(j*10)+i,0);
        }
        if(i-2 >= 0 & j+1 < y){
            this.gf.insertarArista((j*10)+i,(((j+1)*10)+(i-2)),0);
            this.gf.insertarArista((((j+1)*10)+(i-2)),(j*10)+i,0);
        }
        if(i-2 >= 0 & j-1 >= 0){
            this.gf.insertarArista((j*10)+i,(((j-1)*10)+(i-2)),0);
            this.gf.insertarArista((((j-1)*10)+(i-2)),(j*10)+i,0);
        }
        if(i+1 < x & j+2 < y){
            this.gf.insertarArista((j*10)+i,(((j+2)*10)+(i+1)),0);
            this.gf.insertarArista((((j+2)*10)+(i+1)),(j*10)+i,0);
        }
        if(i-1 >= 0 & j+2 < y){
            this.gf.insertarArista((j*10)+i,(((j+2)*10)+(i-1)),0);
            this.gf.insertarArista((((j+2)*10)+(i-1)),(j*10)+i,0);
        }
        if(i+1 < x & j-2 >= 0){
            this.gf.insertarArista((j*10)+i,(((j-2)*10)+(i+1)),0);
            this.gf.insertarArista((((j-2)*10)+(i+1)),(j*10)+i,0);
        }
        if(i-1 >= 0 & j-2 >= 0){
            this.gf.insertarArista((j*10)+i,(((j-2)*10)+(i-1)),0);
            this.gf.insertarArista((((j-2)*10)+(i-1)),(j*10)+i,0);
        }
    }
    
    public static void main(String args[]){
        HorseHamilt he = new HorseHamilt(5,5,0);
    }

}        