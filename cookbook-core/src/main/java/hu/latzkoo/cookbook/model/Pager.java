package hu.latzkoo.cookbook.model;

public class Pager {

    private int from = 0;
    private int items = 2;
    private int pages = 1;

    public Pager(int currentPage, int count) {
        pages = (int) Math.ceil(count / items);

        int page = 1;
        if (currentPage > 0) page = currentPage;
        if (page > pages) page = 1;

        from = items * page - items;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}
