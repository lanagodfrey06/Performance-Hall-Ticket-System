public class BasketItem {
    public LiveEvent event;
        public int qty;
        public BasketItem(LiveEvent event, int qty) {
            this.event = event;
            this.qty = qty;
        }
}

// represents an item in the basket
// contains the event and the quantity of that event
// the event is a LiveEvent object