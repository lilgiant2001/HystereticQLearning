import java.io.*;
import java.util.Random;

public class AgentBG {
    int actionCount; // Number of actions that an agent can have

    public double[][] Q; // Q learning table for agent 1

    private double gamma = 0.9;
    private double alpha = 0.1;
    private double beta = 0.01;
    private int states = 0;

    public double epsilon = 0.25; // Eagerness - 0 looks in the near future, 1 looks in the distant future

    // constructor
    public AgentBG() {

    }

    // Set Q values to 0
    void initializeQ(int states, int act) {
        actionCount = act;
        this.states = states;
        Q = new double[states][actionCount];
        for (int i = 0; i < states; i++) {
            for (int j = 0; j < actionCount; j++) {
                Q[i][j] = 0;
            }
        }
    }

    void clearQtable() {
        for (int i = 0; i < states; i++) {
            for (int j = 0; j < actionCount; j++) {
                Q[i][j] = 0;
            }
        }
    }

    // choose an action based on agent's q table using epsilon greedy
    // returns 0 or 1 depending on action
    int chooseAction(int state) {
        Random rand = new Random();
        double pos = rand.nextDouble();
        if (pos >= epsilon) {
            // exploit

            // search the q value and choose the action
            double maxValue = Double.MIN_VALUE;
            int action = 0;
            int bestAction = 0;

            // search q value table and return the action with maximum q value
            while (action < Q[0].length) {
                if (Q[state][action] >= maxValue) {
                    maxValue = Q[state][action];
                    bestAction = action;
                }
                action++;
            }

            return bestAction;
        } else {
            // explore
            // come up with a random number from 0 to 2
            Random rand2 = new Random();
            int randAction = rand2.nextInt(2);

            return randAction;

        }

    }

    double getMaxQ(int state) {
        // search the q value and choose the maximum Q value
        double maxValue = Double.MIN_VALUE;
        int action = 0;

        // search q value table and return the action with maximum q value
        while (action < Q[0].length) {
            if (Q[state][action] > maxValue) {
                maxValue = Q[state][action];
            }
            action++;
        }

        return maxValue;
    }

    void updateQ(int reward, int action, int state, int newstate) {

        double q = Q[state][action];

        double maxQ = getMaxQ(newstate);

        double delta = reward + gamma * maxQ;

        if (delta >= q) {
            Q[state][action] = (1 - alpha) * q + alpha * delta;
        } else {
            Q[state][action] = (1 - beta) * q + beta * delta;
        }

        // double q = Q[state][action];

        // double maxQ = getMaxQ(newstate);

        // double delta = reward + gamma * maxQ - q;

        // if (delta >= 0) {
        // Q[state][action] = q + alpha * delta;
        // } else {
        // Q[state][action] = q + beta * delta;
        // }

    }

    public static void main(String[] args) {

    }
}
