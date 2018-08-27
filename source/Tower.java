package source;

import java.util.ArrayList;
import java.io.FileWriter;

class Tower {

    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);

        StringBuilder str = new StringBuilder();

        String className = ((Aircraft)flyable).getClass().getSimpleName();
        String aircraftName = ((Aircraft)flyable).name;
        str.append("Tower says: ").append(className).append('#').append(aircraftName).append('(')
                .append(((Aircraft)flyable).id).append(')').append(" registered to weather tower.\n");

        try {
            FileWriter fileWriter = new FileWriter("simulation.txt", true);
            fileWriter.append(str.toString());
            fileWriter.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void unregister(Flyable flyable) {
        for(int i = 0; i < observers.size(); i++) {
            if(((Aircraft) observers.get(i)).id == ((Aircraft) flyable).id)
                observers.remove(i);
        }

        StringBuilder str = new StringBuilder();

        String className = ((Aircraft)flyable).getClass().getSimpleName();
        String aircraftName = ((Aircraft)flyable).name;
        str.append("Tower says: ").append(className).append('#').append(aircraftName).append('(')
                .append(((Aircraft)flyable).id).append(')').append(" unregistered from weather tower.\n");

        try {
            FileWriter fileWriter = new FileWriter("simulation.txt", true);
            fileWriter.append(str.toString());
            fileWriter.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }

        if (observers.size() == 0)
            try {
                FileWriter fileWriter = new FileWriter("simulation.txt", true);
                fileWriter.append("There is no aircrafts, registered in tower\n");
                fileWriter.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
    }
}