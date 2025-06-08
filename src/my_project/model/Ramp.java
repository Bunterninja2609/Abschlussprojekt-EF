package my_project.model;

import my_project.control.ProgramController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ramp {
    //based on Blenders Colorramp
    ArrayList<Spot> spots;
    SpotComparator spotComparator;
    public Ramp(){
        spots = new ArrayList<Spot>();
        spotComparator = new SpotComparator();
    }
    public void addSpot(double pos, double output){
        spots.add(new Spot(pos, output));
    }
    public void sort(){
        Collections.sort(spots, spotComparator);
    }
    private class Spot {
        double position;
        double output;
        public Spot(double position, double output){
            this.position = position;
            this.output = output;
        }
        public double getOutput() {
            return output;
        }
        public double getPosition() {
            return position;
        }
    }
    private class SpotComparator implements Comparator<Spot>{
        @Override
        public int compare(Spot o1, Spot o2) {
            if (o1.getPosition() > o2.getPosition()) {
                return 1;
            }
            if (o1.getPosition() < o2.getPosition()) {
                return -1;
            }
            return 0;
        }
    }
    public double getValue(double position){
        Collections.sort(spots, spotComparator);
        double value = 0.0;
        Spot spot1 = spots.get(0);
        Spot spot2 = spots.get(0);
        for (int i = 0; i < spots.size() - 1; i++) {
            if (spots.get(i).getPosition() < position) {
                spot1 = spots.get(i);
            } else if(spots.get(i).getPosition() == position) {
                return spots.get(i).getOutput();
            } else if(i + 1 >= spots.size() - 1) {
                return spots.get(i).getOutput();
            }else{
                spot2 = spots.get(i);
            }

        }
        double difference = spot2.getPosition() - spot1.getPosition();
        double relativePosition = position - difference;
        double mask = relativePosition / difference;
        value = ProgramController.extrapolate(spot1.getOutput(), spot2.getOutput(), mask, "sine");
        return value;
    }
}
