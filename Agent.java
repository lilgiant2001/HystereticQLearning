import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Agent {

    int actionCount; // Number of actions that an agent can have

    public double[] Q; // Q learning table for agent 1

    private double gamma = 0;
    private double alpha = 0.1;
    private double beta = 0.01;

    public boolean firstTry = true;

    public int count = 0;
    // public double epsilon = 0.05; // Eagerness - 0 looks in the near future, 1
    // looks in the distant future
    // 1/T

    // constructor
    public Agent() {

    }

    // Set Q values to 0
    void initializeQ(int act) {
        actionCount = act;
        Q = new double[actionCount];
        for (int i = 0; i < actionCount; i++) {
            Q[i] = 0;
        }
    }

    // choose an action based on agent's q table using epsilon greedy
    // returns 0, 1, or 2 depending on action
    int chooseAction(double epsilon) {
        Random rand = new Random();
        double pos = rand.nextDouble();
        if (pos >= epsilon) {
            // exploit

            // first try method
            // if (firstTry) {
            // Random rand2 = new Random();
            // int randAction = rand2.nextInt(Q.length);
            // firstTry = false;
            // return randAction;
            // }
            // search the q value and choose the action
            double maxValue = Double.MIN_VALUE;
            int action = 0;
            int bestAction = 0;

            // search q value table and return the action with maximum q value
            while (action < Q.length) {
                if (Q[action] >= maxValue) {
                    maxValue = Q[action];
                    bestAction = action;
                }
                action++;
            }

            return bestAction;
        } else {
            // explore
            // come up with a random number from 0 to 3
            Random rand2 = new Random();
            int randAction = rand2.nextInt(Q.length);

            return randAction;

        }

    }

    int chooseGreedy() {
        // search the q value and choose the action
        double maxValue = Double.MIN_VALUE;
        int action = 0;
        int bestAction = 0;

        // search q value table and return the action with maximum q value
        while (action < Q.length) {
            if (Q[action] > maxValue) {
                maxValue = Q[action];
                bestAction = action;
            }
            action++;
        }
        return bestAction;
    }

    double getMaxQ() {
        // search the q value and choose the maximum Q value
        double maxValue = Double.MIN_VALUE;
        int action = 0;

        // search q value table and return the action with maximum q value
        while (action < Q.length) {
            if (Q[action] > maxValue) {
                maxValue = Q[action];
            }
            action++;
        }

        return maxValue;
    }

    void clearQtable() {
        for (int i = 0; i < actionCount; i++) {
            Q[i] = 0;
        }
    }

    void updateQ(int reward, int action) {

        // double q = Q[action];

        // double maxQ = getMaxQ();

        // double delta = reward + gamma * maxQ;

        // if (delta >= q) {
        // Q[action] = (1 - alpha) * q + alpha * delta;
        // } else {
        // Q[action] = (1 - beta) * q + beta * delta;
        // }

        double q = Q[action];

        double maxQ = getMaxQ();

        double delta = reward + gamma * maxQ - q;

        if (delta >= 0) {
            Q[action] = q + alpha * delta;
        } else {
            Q[action] = q + beta * delta;
        }
    }

    // This is how our main function look like

    // agent1.initializeQ
    // agent2.initializeQ
    // initialize reward table
    // agent1.chooseAction()
    // agent2.chooseAction();
    // getReward( from table based on actions)
    // update Q tables (agent 1 and 2)
    // loop step 4 - 7 until 10000 times

}
