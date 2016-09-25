package eu.parcifal.plus.logic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The MONITOR provides a gateway through wich its owner OBJECT can communicate
 * updates to any SUBSCRIBERs that are subscribed. It is advised to make the
 * MONITOR a FINAL field of the owner, providing easy access for potential
 * SUBSCRIBERs.
 *
 * @version 2016.09.25
 */
public class Monitor {

    /**
     * The owner OBJECT of the current MONITOR.
     */
    public final Object owner;

    /**
     * The COLLECTION of SUBSCRIBERs that are subscribed to the current
     * MONITOR.
     */
    private final Collection<Subscriber> subscribers = new ArrayList<Subscriber>();

    /**
     * Construct a MONITOR with the specified OBJECT as its owner.
     *
     * @param owner The OBJECT to be the owner of the MONITOR to construct.
     */
    public Monitor(Object owner) {
        this.owner = owner;
    }

    /**
     * Inform all SUBSCRIBERs of the current MONITOR of the specified argument
     * OBJECTs if the specified caller OBJECT is equal to the owner OBJECT of
     * the ccurrent MONITOR.
     *
     * @param caller The OBJECT that must be equal to the owner OBJECT of the
     *               current MONITOR.
     * @param arguments The argument OBJECTs that will be used to inform the
     *                  SUBCRIBERs of the current MONITOR.
     */
    public void call(Object caller, Object... arguments) {
        if(this.owner.equals(caller)) {
            this.subscribers.forEach(subscriber -> subscriber.inform(this, arguments));
        } else {
            throw new IllegalArgumentException("Specified caller OBJECT is not equal to the owner OBJECT of the current MONITOR");
        }
    }

    /**
     * Subscribe the specified SUBSCRIBER to the current MONITOR if it is not
     * already subscribed to the current MONITOR.
     *
     * @param subscriber The SUBSCRIBER to be subscribed to the current
     *                   MONITOR.
     */
    public void subscribe(Subscriber subscriber) {
        if(!this.subscribers.contains(subscriber)) {
            this.subscribers.add(subscriber);
        } else {
            throw new IllegalArgumentException("Specified SUBSCRIBER is already subscribed to the current MONITOR");
        }
    }

    /**
     * Unsubscribe the specified SUBSCRIBER to the current MONITOR if it is
     * subscribed to the current MONITOR.
     *
     * @param subscriber The SUBSCRIBER to be unscubscribed to the current
     *                   MONITOR.
     */
    public void unsubscribe(Subscriber subscriber) {
        if(this.subscribers.contains(subscriber)) {
            this.subscribers.remove(subscriber);
        } else {
            throw new IllegalArgumentException("Specified SUBSCRIBER is not subscribed to the current MONITOR");
        }
    }

    /**
     * The SUBSCRIBER interface must be implemented by all OBEJCTs that are
     * required to SUBSCRIBE to a MONITOR.
     *
     * @version 2016.09.25
     */
    public interface Subscriber {

        /**
         * Inform the current SUBSCRIBER about the specified argument OBJECTs,
         * sent by the owner of the specified MONITOR.
         *
         * @param monitor The MONITOR that is informing the current SUBSCRIBER
         *                about the specified argument OBJECTs.
         * @param arguments The argument OBJECTs sent by the owner of the
         *                  specified MONITOR.
         */
        void inform(Monitor monitor, Object... arguments);

    }

}
