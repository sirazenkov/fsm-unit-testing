import java.util.List;

public class AntFSM {
    private static String[] states = {"Staying_at_home", "Searching_for_a_leaf", "Carrying_the_leaf", "Escaping_from_a_predator"};
    private int state = 0;

    public String getState() {
        if(state >= 0 && state <= 3)
            return states[state];
        else
            throw new RuntimeException("Invalid state value!");
    }

    int returnSpeed(boolean atHome, boolean foundLeaf, boolean seePredator) {
        switch(state) {
            case(0):    // Staying_at_home
                if(!seePredator) {
                    if(foundLeaf) {
                        state = 2;
                        return 1;
                    } else {
                        state = 1;
                        return 5;
                    }
                } else {
                    return 0;
                }
            case(1):    // Searching_for_a_leaf
                if(seePredator) {
                    if(atHome) {
                        state = 0;
                        return 0;
                    } else {
                        state = 3;
                        return 10;
                    }
                } else {
                    if(foundLeaf) {
                        state = 2;
                        return 1;
                    } else {
                        return 5;
                    }
                }
            case(2):    // Carrying_the_leaf
                if(atHome) {
                    state = 0;
                    return 0;
                } else if(seePredator) {
                    state = 3;
                    return 10;
                } else {
                    return 1;
                }
            case(3):    // Escaping_from_predator
                if(!seePredator) {
                    if(!foundLeaf) {
                        state = 1;
                        return 5;
                    } else {
                        state = 2;
                        return 1;
                    }
                } else {
                    if(atHome) {
                        state = 0;
                        return 0;
                    } else {
                        return 10;
                    }
                }
            default:
                throw new RuntimeException("Invalid state value!");
        }
    }
}
