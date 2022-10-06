import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	private static String SEPARACION = "------------------------------------------------------------";
	
	public static void main(String[] args) {
		System.out.println("EJERCICIO 1");
		ejercicio1Imperativa();
		ejercicio1Funcional();
		System.out.println(SEPARACION);
		System.out.println("JERCICIO 2");
		ejercicio2();
		System.out.println(SEPARACION);
	}
	
	private static void ejercicio1Imperativa() {
		List<Integer> numbers =  List.of(18, 6, 4, 15, 55, 78, 12, 9, 8);
		int numbersBiggerThan10 = 0;
		
		for (Integer integer : numbers) {
			if(integer > 10) {
				numbersBiggerThan10++;
			}
		}
		System.out.println("IMPERATIVA: " +numbersBiggerThan10);
	}
	
	private static void ejercicio1Funcional() {
		List<Integer> numbers =  List.of(18, 6, 4, 15, 55, 78, 12, 9, 8);
		int numbersBiggerThan10 = 0;
		
		numbersBiggerThan10 = numbers.stream().filter(numero -> numero > 10).toList().size();
		System.out.println("FUNCIONAL: " +numbersBiggerThan10);
	}
	
	private static void ejercicio2() {
		List<Product> shoppingCart = List.of(
				new Product("Clothes", new BigDecimal("15.90"), Tax.NORMAL),
				new Product("Bread", new BigDecimal("1.5"), Tax.SUPERREDUCED),
				new Product("Meat", new BigDecimal("13.99"), Tax.REDUCED),
				new Product("Cheese", new BigDecimal("3.59"), Tax.SUPERREDUCED),
				new Product("Coke", new BigDecimal("1.89"), Tax.REDUCED),
				new Product("Whiskey", new BigDecimal("19.90"), Tax.NORMAL));
	
		BigDecimal precioTotalSinImpuestos = new BigDecimal(shoppingCart.stream().mapToDouble(product -> product.price.doubleValue()).sum());
		System.out.println("Precio total SIN IMPUESTOS: "+precioTotalSinImpuestos);
		
		BigDecimal precioTotalConImpuestos = new BigDecimal(shoppingCart.stream().mapToDouble(product -> 
			product.price.doubleValue()*(1.00+((double)product.tax.getPercent()/100))).sum());
		System.out.println("Precio total CON IMPUESTOS: "+precioTotalConImpuestos);
		
		String productosOrdenados = shoppingCart.stream()
				.filter(x -> x.name.startsWith("C"))
				.sorted((x, y) -> x.name.compareToIgnoreCase(y.name))
				.map(x -> x.name)
				.collect(Collectors.joining(",","Products starts with C are ",","));
		System.out.println(productosOrdenados);;
		
		BigDecimal precioTotalConImpuestosMayorDe10 = new BigDecimal(shoppingCart.stream()
				.filter(producto -> producto.price.doubleValue() > 10)
				.mapToDouble(product -> 
		product.price.doubleValue()*(1.00+((double)product.tax.getPercent()/100))).sum());
		System.out.println("Precio total CON IMPUESTOS DE PRODUCTOS DE MAS DE 10: "+precioTotalConImpuestosMayorDe10);
	}

}
