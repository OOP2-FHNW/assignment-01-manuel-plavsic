package portfolio;

import portfolio.investments.Investment;
import portfolio.investments.Share;

import java.util.*;

public class Portfolio <T extends Investment> {


    Map<String, Investment> container = new TreeMap<>();


    boolean contains(Investment investment) {
        return container.containsKey(investment.getTitel());
    }


    /**
     * Removes the investment from the list if the count is 0.
     * @param inContainer
     */
    void update(Investment inContainer) {
        if (inContainer.getCount() == 0) {
            container.remove(inContainer.getTitel());
        }
    }


    void buy(Investment investment) {
        if (investment.getCount() == 0)
            return; // nothing will change

        if (!contains(investment)) {
            container.put(investment.getTitel(), investment);
        } else {
            container.get(investment.getTitel()).setCount(
                    container.get(investment.getTitel()).getCount() + investment.getCount()
            );
            update(container.get(investment.getTitel())); // if the counts of the the investments had different sign
                    // (i.e. one was positive and the other negative), our count could have changed to 0.
        }
    }


    public void sell(String key, int i) {
        if (!container.containsKey(key)) {
            // throw new Exception("The key " + key + " is not present in this portfolio.");
            System.out.println("The key " + key + " is not present in this portfolio.");
        } else {
            if (container.get(key).getCount() < i) {
                // throw new Exception("The key " + key + " in this portfolio only has " +
                //         container.get(key).getCount() + " count value.");
                System.out.println("The key " + key + " in this portfolio only has " +
                        container.get(key).getCount() + " count value.");
            } else {
                container.get(key).setCount(
                        container.get(key).getCount() - i
                );

                update(container.get(key));
            }
        }
    }


    /**
     * Returns a share. Since a resource is not a share, it cannot be a valid element to return.
     * @param key
     * @return
     */
    public Investment getShare(String key) {
        if (!container.containsKey(key) || !(container.get(key) instanceof Share)) {
            return null;
        } else {
            return container.get(key);
        }
    }
}
