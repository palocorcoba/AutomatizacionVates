package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class Utiles {
	
	/**
	 * Retorna la fecha actual con formato String dd/mm/yyyy.
	 * @return String fecha actual.
	 */
	public static String getFechaActual()
	{
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyymms");
		String date = format.format(new Date());
	    return date;
	}
	/**
	 * Genera aleatoriamente caracteres para formar un String de la cantidad de
	 * caracteres recibida por parametro. Si dicha cantidad es mayor a 30,
	 * generara un String compuesto por las palabras "TestAutomatizado" mas la
	 * fecha actual mas caracteres al azar. Si la cantidad es menor o igual a
	 * 30, generara un String compuesto por las siglas "TA" mas caracteres al
	 * azar.
	 * 
	 * @param tamMaximo
	 *            Cantidad maxima de caracters que puede tener el String generado.
	 * @return String generado.
	 */
	public static String generateString(int tamMaximo)
	{
		String r = "TestAutomatizado " + getFechaActual() + " ";
		int tamano = tamMaximo-28;
		if (tamano < 2)
		{
			r = "TA ";
			tamano = tamMaximo-3;
		}
		r += RandomStringUtils.randomAlphanumeric(tamano);
		
		return r;
	}
}
