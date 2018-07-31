package ausc.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public final class DataSource {
	static int weight;
	
	private static PropertyChangeSupport pcs = new PropertyChangeSupport(Object.class);

	private final static List<PropertyChangeListener> listeners = new ArrayList<>();
	
	public DataSource(String weight) {
		super();
		DataSource.setWeight(weight);
	}
	
	public static void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public static void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
	public static void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private static void firePropertyChange(String property, Object oldValue, Object newValue) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(property, property, oldValue, newValue));
        }
    }
	
	

	public static int getWeight() {
		return weight;
	}
	
	public static String getWeightString() {
		return String.valueOf(weight);
	}

	public static void setWeight(String weight) {
		int oldValue = DataSource.weight;
		DataSource.weight = Integer.parseInt(weight);
		firePropertyChange("weight", oldValue, weight);
	}

	

}
