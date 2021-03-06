package suicidesquad.primavera.test.entrega;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

@Component
public class TheBeatles implements Banda
{
   @Injected(implementation=GeorgeHarrison.class)
   private Guitarrista primeraGuitarra;

   @Injected(implementation=JohnLennon.class)
   private Guitarrista segundaGuitarra;

   @Injected
   private Bajista bajista;

   @Injected
   private Baterista baterista;

   public String toString()
   {
      String ret = "";
      ret+="The Beatles { \n";
      ret+="       "+primeraGuitarra+"\n";
      ret+="      ,"+segundaGuitarra+"\n";
      ret+="      ,"+bajista+"\n";
      ret+="      ,"+baterista+" }\n";
      return ret;
   }
}
