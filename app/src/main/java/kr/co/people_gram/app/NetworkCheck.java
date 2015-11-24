package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-11-23.
 */
public class NetworkCheck {
    static Boolean network = false;

    public void set_network(Boolean network) {
        this.network = network;
    }
    public Boolean get_network() { return this.network; }
}
