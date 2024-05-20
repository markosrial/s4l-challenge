package com.markosrial.s4lchallenge.domain.bookings.selectors;

import com.markosrial.s4lchallenge.domain.bookings.Booking;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Booking selector that retrieves the best profit combination.
 * The algorithm will treat the bookings list as a tree with all the nodes as start candidates,
 * then it will look recursively for the best profitable path for each initial node.
 */
public class BookingProfitOptimizer implements BookingSelector {

    public static BookingProfitOptimizer INSTANCE = new BookingProfitOptimizer();

    /**
     * Auxiliary structure (tree node) for the path exploration.
     */
    @Getter
    @Setter
    private class BookingNode {

        private Booking booking;

        private BookingNode next;

        private double accumProfit;

        private boolean isLeaf;

        public BookingNode(Booking initialNode) {
            this.booking = initialNode;
            this.isLeaf = false;
        }

    }

    @Override
    public List<Booking> select(List<Booking> bookings) {

        // Return empty if no bookings
        if (bookings.isEmpty()) {
            return List.of();
        }

        // Transform to node representation and order by checkIn date to optimize the next node search
        List<BookingNode> initialList = bookings.stream()
                .sorted(Comparator.comparing(b -> b.getCheckIn()))
                .map(b -> new BookingNode(b))
                .toList();

        // Search the best path for each node
        BookingNode bestPath = initialList.get(0);
        for (int i = 0; i < initialList.size(); i++) {
            BookingNode node = generateProfitablePath(i, initialList);
            if (node.getAccumProfit() > bestPath.getAccumProfit()) {
                bestPath = node;
            }
        }

        // Flat the best path calculated to a list
        return flatNodePath(bestPath);

    }

    /**
     * Generates the best profitable path for each starting node.
     * @param startNode the start node position
     * @param list the list with the nodes
     * @return the selected node, linked to his best path combination
     */
    private BookingNode generateProfitablePath(int startNode, List<BookingNode> list) {

        // The selected node where operate
        BookingNode currentNode = list.get(startNode);

        // Move pointer to the next candidate node (the nearest which checkIn starts after the current checkOut).
        // When there is no condition matching the break point, it indicates that the node is a leaf.
        int nextNode = startNode + 1;
        while (nextNode < list.size()) {
            // When current node checkout date doesn't end before next node, then we must skip it
            if (!currentNode.getBooking().getCheckOut().isBefore(list.get(nextNode).getBooking().getCheckIn())) {
                nextNode++;
                continue;
            }
            break;
        }

        // Get the best next profitable node
        BookingNode profitableNode = null;
        for (int i = nextNode; i < list.size(); i++) {

            BookingNode iNode = list.get(i);

            // When not previously processed, apply recursive function generating the profitable path for the iNode.
            if (!iNode.isLeaf() && iNode.getNext() == null) {
                iNode = generateProfitablePath(i, list);
            }

            // When the new generated path is a better match, replace the current one
            if (profitableNode == null || profitableNode.getAccumProfit() < iNode.getAccumProfit()) {
                profitableNode = iNode;
            }

        }

        // When there is a profitable path available, then we need to link it to the current one and accumulate the profit
        if (profitableNode != null) {
            currentNode.setAccumProfit(currentNode.getBooking().getProfit() + profitableNode.getAccumProfit());
            currentNode.setNext(profitableNode);
        }
        // Else the node is a leaf, so we set the necessary node values
        else {
            currentNode.setAccumProfit(currentNode.getBooking().getProfit());
            currentNode.setLeaf(true);
        }

        return currentNode;

    }

    /**
     * Flattens the linked node path
     * @param nodePath the node path
     * @return the flattened list of bookings combination
     */
    private List<Booking> flatNodePath(BookingNode nodePath) {

        List<Booking> list = new ArrayList<>();
        BookingNode nextNode = nodePath;

        while (!nextNode.isLeaf()) {
            list.add(nextNode.getBooking());
            nextNode = nextNode.getNext();
        }
        list.add(nextNode.getBooking());

        return list;

    }


}
