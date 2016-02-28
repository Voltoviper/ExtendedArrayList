package voltoviper;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;

/**
 * Diese ArrayList bietet die Möglichkeit eine 3-spaltige ArrayListe aufzubauen. Es werden hierbei für jede Spalte eine
 * neue ArrayList angelegt auf denen dann alle Aktionen gleichzeitig ausgeführt werden. Durch eine Hash-Überprüfung wird
 * sichergestellt, dass die Daten nicht durch irgendwelche Umstände verändert werden können.
 * @author Christoph Nebendahl
 */
public abstract class ExtendedArrayList<T, U, V>{

    ObjectProperty<ArrayList<T>> data1;
    ObjectProperty<ArrayList<U>> data2;
    ObjectProperty<ArrayList<V>> data3;
    ObjectProperty<ArrayList<String>> hashlist;

    /**
     *
     */
    public ExtendedArrayList() {
        data1 = new SimpleObjectProperty<>(new ArrayList<>());
        data2 = new SimpleObjectProperty<>(new ArrayList<>());
        data3 = new SimpleObjectProperty<>(new ArrayList<>());
        hashlist = new SimpleObjectProperty<>(new ArrayList<>());

    }

    /**
     * Fügt die übergebenen Objekte in die ExtendedArrayList ein, und überprüft anschließend, ob die Daten korrekt eingetragen wurden.
     *
     * @param data1 Object1 dass eingetragen werden soll.
     * @param data2 Object2 dass eingetragen werden soll.
     * @param data3 Object3 dass eingetragen werden soll.
     * @throws Exception sobald beim Eintragen ein Fehler auftritt.
     */
    public void add(T data1, U data2, V data3){
        this.data1.getValue().add(data1);
        this.data2.getValue().add(data2);
        this.data3.getValue().add(data3);
        this.hashlist.getValue().add(generateHash(data1, data2, data3));
        try {
            checkData(data1, data2, data3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generiert einen HashString der aus den drei übergebenen Objekten zusammengesetzt wird.
     *
     * @param data1 Objekt 1, aus dem der HashWert berechnet werden soll.
     * @param data2 Objekt 2, aus dem der HashWert berechnet werden soll.
     * @param data3 Objekt 3, aus dem der HashWert berechnet werden soll.
     * @return Gibt einen String zurück, der den zusammengesetzten HashString enthält.
     */
    private String generateHash(T data1, U data2, V data3) {
        StringBuilder hash = new StringBuilder();
        hash.append(data1.hashCode());
        hash.append(data2.hashCode());
        hash.append(data3.hashCode());
        return hash.toString();
    }

    /**
     * Überprüft die gesamte ExtendedArrayList, ob die integrität gegeben ist.
     *
     * @return Gibt true zurück, wenn alle Elemente korrekt liegen.
     * @throws IntegrityException wird geworfen, sobald ein Hashwert oder Länge der ArrayList nicht übereinstimmt.
     */
    public boolean check() throws IntegrityException {
        for (int i = 0; i < getMaxSize(); i++) {
            if (!generateHash(data1.getValue().get(i), data2.getValue().get(i), data3.getValue().get(i)).equals(hashlist.getValue().get(i))) {
                throw new IntegrityException("List Elements are not equal");
            }
        }
        return true;
    }

    /**
     * Gibt die Größe der Listen zurück und überprüft, ob diese gleich groß sind.
     *
     * @return Integer Wert der die Größe der Listen darstellt
     * @throws IntegrityException wird geworfen, sobald die Listen nicht gleich groß sind.
     */
    private int getMaxSize() throws IntegrityException {
        int size = data1.getValue().size();
        if (data2.getValue().size() != size) {
            throw new IntegrityException("Lists Size are not equal");
        }
        if (data3.getValue().size() != size) {
            throw new IntegrityException("Lists Size are not equal");
        }
        if (hashlist.getValue().size() != size) {
            throw new IntegrityException("Lists Size are not equal");
        }
        return size;
    }

    /**
     * Überprüft, ob die Integrität der Extended ArrayList gegeben ist.
     *
     * @param data1 Objekt1 das überprüft werden muss
     * @param data2 Objekt2 das überprüft werden muss
     * @param data3 Objekt3 das überprüft werden muss
     * @return Gibt ein true zurück, sobald alle 3 Objekte an der gleichen ID stehen
     * @throws IntegrityException Wirft eine Integrity Exception sobald die Integrität der gegeben Daten nicht übereinstimmt.
     */
    private boolean checkData(T data1, U data2, V data3) throws Exception {
        int id = this.data1.getValue().indexOf(data1);
        if ((this.data2.getValue().indexOf(data2) == id) && (this.data3.getValue().indexOf(data3) == id)) {
            return true;
        } else {
            throw new IntegrityException("ExtendedArrayList not safe!");
        }
    }

    /**
     * Gibt eon Object[] zurück, der die Werte an der angegebenen Stelle der ArrayList zurück.
     * @param i Stelle, dessen Werte zurückgegeben werden sollen.
     * @return Gibt ein Ibject[] zurück, sobald während der Abfrage weder eine Integritätats Exception festgestellt wird,
     * noch eine Stelle ausgewählt wird, die nicht vorhanden ist.
     */
    public Object[] get(int i){
        Object[] objects = null;
        try {
            check();
            objects = new Object[3];
            objects[0] = data1.getValue().get(i);
            objects[1] = data2.getValue().get(i);
            objects[2] = data3.getValue().get(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * Gibt die 1. ArrayList zurück
     * Diese Methode sollte nicht von außen verwendet werden. Da bei Änderungen die Hash Werte nicht mehr mit der Referenz Liste übereinstimmt!!!
     * @return ArrayList der entsprechenden Klasse
     */
    private ArrayList<T> getDataList1(){
        return data1.getValue();
    }

    /**
     * Gibt die 2. ArrayList zurück
     * Diese Methode sollte nicht von außen verwendet werden. Da bei Änderungen die Hash Werte nicht mehr mit der Referenz Liste übereinstimmt!!!
     * @return ArrayList der enrsprechenden Klasse
     */
    private ArrayList<U> getDataList2(){
        return data2.getValue();
    }
    /**
     * Gibt die 3. ArrayList zurück
     * Diese Methode sollte nicht von außen verwendet werden. Da bei Änderungen die Hash Werte nicht mehr mit der Referenz Liste übereinstimmt!!!
     * @return ArrayList der enrsprechenden Klasse
     */
    private ArrayList<V> getDataList3(){
        return data3.getValue();
    }
    /**
     * Gibt die ArrayList mit den Hash Werten zurück
     * Diese Methode sollte nicht von außen verwendet werden. Sobald die Werte nicht mehr mit den anderen Listen übereinstimmt ist die Integrität nicht mehr gewährleistet werden.
     * @return ArrayList der enrsprechenden Klasse
     */
    private ArrayList<String> getHashList(){
        return hashlist.getValue();
    }
}

