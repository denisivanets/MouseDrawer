import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KMeans {

    private int K = 4;
    private List<Cluster> currentClusterState = new ArrayList<>(K);
    private List<Cluster> previousClusterState = new ArrayList<>(K);
    private boolean isClustersCreated = false;

    public void runAlgorithm(int width, int height){
        if(isClustersCreated == true) return;
        for (int i = 0; i < K; i++){
            Cluster cluster = new Cluster((int) (Math.random() * width), (int) (Math.random() * height));
            currentClusterState.add(cluster);
        }
        makeDependecies();
        copyLists();
        makeIters();
    }

    private void makeIters(){
        while (isStateChanged() == true) {
            copyLists();
            changeCenters();
        }
        isClustersCreated = true;
        currentClusterState.forEach(
                (cluster) -> {
                    cluster.getPointList().forEach(
                            (point) -> System.out.println("X: " + point.getX() + "Y: " + point.getY())
                    );
                    System.out.println("======================");
                }
        );
    }

    private double countDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt( Math.pow((x2 - x1),2) + Math.pow( (y2 - y1),2) );
    }

    private boolean isStateChanged(){
        for (int i = 0; i < currentClusterState.size(); i++){
            if (!previousClusterState.get(i).equals(currentClusterState.get(i))) return true;
        }
        return false;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public List<Cluster> getCurrentClusterState() {
        return currentClusterState;
    }

    public void setCurrentClusterState(List<Cluster> currentClusterState) {
        this.currentClusterState = currentClusterState;
    }

    public List<Cluster> getPreviousClusterState() {
        return previousClusterState;
    }

    public void setPreviousClusterState(List<Cluster> previousClusterState) {
        this.previousClusterState = previousClusterState;
    }

    public void changeCenters(){
        currentClusterState.forEach(
                (cluster) -> cluster.recalcCenter()
        );
    }

    public void makeDependecies(){
        for (Point point : PointStorage.getInstance().getPointList()){
            double min = countDistance( point.getX(), point.getY(), currentClusterState.get(0).getX(), currentClusterState.get(0).getY());
            Cluster targetCluster = currentClusterState.get(0);
            for (Cluster cluster : currentClusterState){
                double distance = countDistance( point.getX(), point.getY(), cluster.getX(), cluster.getY());
                if ( distance < min ){
                    min = distance;
                    targetCluster = cluster;
                }
            }
            if (!point.isAlreadyAdded()) {
                targetCluster.addPoint(point);
                point.setIsAlreadyAdded(true);
            }
        }
    }

    private void copyLists(){
        currentClusterState.forEach(
                (element) -> previousClusterState.add(element)
        );
    }

}
