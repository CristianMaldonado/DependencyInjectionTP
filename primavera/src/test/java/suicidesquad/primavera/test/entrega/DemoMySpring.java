package suicidesquad.primavera.test.entrega;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.src.Factory;

@Component
public class DemoMySpring
{
   public static void main(String[] args)
   {
      Banda banda = Factory.getObject(TheBeatles.class);
      System.out.println(banda);
   }
}
