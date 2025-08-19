import dataStructures.UnionFind;
import dataStructures.UnionFindInArray;

import java.util.LinkedList;
import java.util.List;

public class Solver {

    private final int width;
    private final int diameter;
    private int laserCounter;

    private final int[][] laserCoords;

    private final UnionFind partition;

    private final List<Integer>[] laserGroups;

    @SuppressWarnings("unchecked")
    public Solver(int width, int diameter, int nlasers) {
        this.width = width;
        this.diameter = diameter;
        laserCoords = new int[nlasers][2];
        partition = new UnionFindInArray(nlasers);
        laserGroups = new List[nlasers];
        laserCounter = 0;
    }

    public void addLaser(int x, int y){
        laserCoords[laserCounter] = new int[]{x, y};
        laserGroups[laserCounter] = new LinkedList<>();
        laserGroups[laserCounter].add(laserCounter);
        int rep1 = laserCounter;
        for(int i = 0; i < laserCounter; i++){
            if(distance(x, y, laserCoords[i][0], laserCoords[i][1]) < diameter){
                int rep2 = partition.find(i);
                if(rep1 != rep2) {
                    partition.union(rep2, rep1);
                    int newRep = partition.find(i);
                    if (newRep == rep1) {
                        laserGroups[newRep].addAll(laserGroups[rep2]);
                        laserGroups[rep2] = new LinkedList<>();
                    } else {
                        laserGroups[newRep].addAll(laserGroups[rep1]);
                        laserGroups[rep1] = new LinkedList<>();
                        rep1 = newRep;
                    }
                }
            }
        }
        laserCounter++;
    }

    public boolean isPossible(){
        for(int i = 0; i < laserCounter; i++){
            int minY = width;
            int maxY = 0;
            for(int index : laserGroups[i]){
                minY = Math.min(minY, laserCoords[index][1]);
                maxY = Math.max(maxY, laserCoords[index][1]);
            }
            if(minY < diameter && width - maxY < diameter){
                return false;
            }
        }
        return true;
    }

    private int distance(int x1, int y1, int x2, int y2){
        int distX = x1-x2;
        int distY = y1-y2;
        return (int) Math.sqrt(distX * distX + distY * distY);
    }
}
