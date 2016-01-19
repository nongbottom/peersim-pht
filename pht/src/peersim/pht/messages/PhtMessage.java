package peersim.pht.messages;

import peersim.core.Node;


/**
 * <p>
 *     PhtMessage is the central message class for all communications at the
 *     Pht level.
 *     It contains constant values for all different type of messages (20
 *     related to requests, and 20 more for the corresponding acquittal).
 * </p>
 *
 * <p>
 *     There is no big enumeration, because we some time need to do some
 *     comparisons.
 * </p>
 *
 * <p>A PhtMessage has:</p>
 * <ol>
 *   <li>
 *       a type, split / suppression / lookup / merge / etc. request
 *   </li>
 *   <li>
 *       a source node (on the peersim level), this is needed for direct
 *       communications between nodes to avoid unnecessary dht-lookup
 *       operations.
 *   </li>
 *   <li>
 *       a source node's (PhtNode) label: this will be used for acquittals
 *   </li>
 *   <li>
 *       an id, each request (and hence each message) must have a unique one for
 *       identification
 *   </li>
 *   <li> more space if needed, like another class in the peersim.pht.messages
 *      package or a boolean, or a data.
 *   </li>
 * </ol>
 */
public class PhtMessage {

    // For the first node on the network
    public static final int INIT = -1;

    /* Indexes */

    public static final int ACK  = 100;

    /* Requests */

    public static final int SPLIT               = 1;
    public static final int SPLIT_DATA          = 2;
    public static final int SPLIT_LEAVES        = 3;
    public static final int UPDATE_PREV_LEAF    = 4;
    public static final int UPDATE_NEXT_LEAF    = 5;
    public static final int MERGE               = 6;
    public static final int NO_MERGE            = 7;
    public static final int MERGE_LEAVES        = 8;
    public static final int MERGE_DATA          = 9;
    public static final int MERGE_DONE          = 10;
    public static final int INSERTION           = 11;
    public static final int SUPRESSION          = 12;
    public static final int UPDATE_NBKEYS_MINUS = 13;
    public static final int UPDATE_NBKEYS       = 14;
    public static final int UPDATE_NBKEYS_PLUS  = 15;
    public static final int LIN_LOOKUP          = 16;
    public static final int BACK_LIN_LOOKUP     = 17;
    public static final int BIN_LOOKUP          = 18;
    public static final int SEQ_QUERY           = 19;
    public static final int PAR_QUERY           = 20;

    /* ACK for requests */

    public static final int ACK_SPLIT               = SPLIT                + ACK;
    public static final int ACK_SPLIT_DATA          = SPLIT_DATA           + ACK;
    public static final int ACK_SPLIT_LEAVES        = SPLIT_LEAVES         + ACK;
    public static final int ACK_UPDATE_PREV_LEAF    = UPDATE_PREV_LEAF     + ACK;
    public static final int ACK_UPDATE_NEXT_LEAF    = UPDATE_NEXT_LEAF     + ACK;
    public static final int ACK_MERGE               = MERGE                + ACK;
    public static final int ACK_MERGE_LEAVES        = MERGE_LEAVES         + ACK;
    public static final int ACK_MERGE_DATA          = MERGE_DATA           + ACK;
    public static final int ACK_MERGE_DONE          = MERGE_DONE           + ACK;
    public static final int ACK_INSERTION           = INSERTION            + ACK;
    public static final int ACK_SUPRESSION          = SUPRESSION           + ACK;
    public static final int ACK_UPDATE_NBKEYS_MINUS = UPDATE_NBKEYS_MINUS  + ACK;
    public static final int ACK_UPDATE_NBKEYS_PLUS  = UPDATE_NBKEYS_PLUS   + ACK;
    public static final int ACK_LIN_LOOKUP          = LIN_LOOKUP           + ACK;
    public static final int ACK_BIN_LOOKUP          = BIN_LOOKUP           + ACK;
    public static final int ACK_SEQ_QUERY           = SEQ_QUERY            + ACK;
    public static final int ACK_PAR_QUERY           = PAR_QUERY            + ACK;
    public static final int ACK_PAR_QUERY_CLIENT    = ACK_PAR_QUERY        + 1;
    public static final int ACK_PAR_QUERY_CLIENT_F  = ACK_PAR_QUERY_CLIENT + 1;

    /* Retry */

    public static final int RETRY = -2063;

    /* General info */

    public static final int LAST_OP  = PAR_QUERY;
    public static final int LAST_ACK = LAST_OP + ACK;

    private int type;
    private Node initiator;
    private String initiatorLabel;
    private Object more;
    private final long id;

    public PhtMessage(Node initiator) {
        this.type           = PhtMessage.INIT;
        this.initiator      = initiator;
        this.initiatorLabel = null;
        this.id             = (long) 0;
    }

    public PhtMessage(int type, Node initiator, String initiatorLabel, long id, Object more) {
        this.type = type;
        this.initiator = initiator;
        this.initiatorLabel = initiatorLabel;
        this.id = id;
        this.more = more;
    }

   /* Getter */

    public Object getMore() {
        return more;
    }

    public long getId() {
        return id;
    }

    public String getInitiatorLabel() {
        return initiatorLabel;
    }

    public Node getInitiator() {
        return initiator;
    }

    public int getType() {
        return type;
    }

    /* Setter */

    public void setInitiator(Node initiator) {
        this.initiator = initiator;
    }

    public void setInitiatorLabel(String initiatorLabel) {
        this.initiatorLabel = initiatorLabel;
    }

    public void setMore(Object more) {
        this.more = more;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (this.initiatorLabel != null) {
            return String.format("%d: init(%d, '%s') :: type: %d\n",
                    this.id, this.initiator.getID(), this.initiatorLabel, this.type);
        }

        return String.format("%d: init(%d, null) :: type: %d\n",
                this.id, this.initiator.getID(), this.type);
    }
}
