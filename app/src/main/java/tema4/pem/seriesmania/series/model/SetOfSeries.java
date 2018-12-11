package tema4.pem.seriesmania.series.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SetOfSeries {

	private static SetOfSeries singleton = null;
	private ArrayList<ItemSeries> listaDeRecetas;
	
	private SetOfSeries() {
		listaDeRecetas = new ArrayList<ItemSeries>();
		listaDeRecetas.add(new ItemSeries("bigBang","The Big Bang Theory","Thermomix"));
		listaDeRecetas.add(new ItemSeries("narcos","Narcos","Thermomix"));
		listaDeRecetas.add(new ItemSeries("happy","Happy!","Vitro"));
		
	}
	public static SetOfSeries getInstance() {
		if (singleton == null)
			singleton = new SetOfSeries();
		return singleton;
	}

	public ArrayList<ItemSeries> getListaDeRecetas() {
		return listaDeRecetas;
	}

	public void setListaDeRecetas(ArrayList<ItemSeries> listaDeRecetas) {
		this.listaDeRecetas = listaDeRecetas;
	}
	
	public void agregarItem(ItemSeries nuevo) {
		listaDeRecetas.add(nuevo);
		Collections.sort(listaDeRecetas, new Comparator<ItemSeries>() {
	        @Override
	        public int compare(ItemSeries item1, ItemSeries item2) {
	            return  item1.getNombreReceta().compareTo(item2.getNombreReceta());
	        }
	    });
	}
}


