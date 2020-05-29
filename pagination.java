public class PaginationRecyclerViewListener extends RecyclerView.OnScrollListener {


    private boolean mIsLoading = false;

    private boolean mIsFirstTimeLoading = false;

    private int mPaginationValue;


    private PaginadoBet mPaginationBet;

    public interface PaginatorRecyclerViewCallBack {
        void onLoadMoreItems();
    }

    private PaginatorRecyclerViewCallBack callBack;

    public PaginationRecyclerViewListener(PaginatorRecyclerViewCallBack callBack) {
        this.callBack = callBack;
        this.initPaginationBet(Constants.CustomPaginationValues.DEFAULT_VALUE);
    }

    public PaginationRecyclerViewListener(PaginatorRecyclerViewCallBack callBack, int paginationValue) {
        this.callBack = callBack;
        this.initPaginationBet(paginationValue);
    }

    /**
     * Just create a instance of @link com.santander.enlace.http.beans.PaginadoBet
     */
    private void initPaginationBet(int paginationValue) {
        mPaginationBet = new PaginadoBet();
        mPaginationBet.setDatosrepo("");
        mPaginationBet.setNumeroRegistrosPagina(String.valueOf(paginationValue));
        mPaginationBet.setIndrepo("");
        mPaginationValue = paginationValue;
        mIsFirstTimeLoading = true;
    }

    public void setPaginationBet(PaginadoBet mPaginationBet) {
        this.mPaginationBet = mPaginationBet;
        this.mIsFirstTimeLoading = false;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int visibleItemCount = manager.getChildCount();
        int totalItemCount = manager.getItemCount();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

        if (mPaginationBet == null)
            return;

        if (totalItemCount == 0)
            return;

        if (!mIsLoading && mPaginationBet.canLoadMoreItems()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= Integer.valueOf(mPaginationBet.getNumeroRegistrosPagina())) {

                if (callBack != null) {
                    mIsLoading = true;
                    callBack.onLoadMoreItems();
                }
            }
        }
    }

    public void notifyIsLoading(Boolean isLoading) {
        this.mIsLoading = isLoading;
    }


    public PaginadoBet getPaginationBet(boolean reInit) {
        if (reInit)
            initPaginationBet(mPaginationValue);
        return mPaginationBet;
    }

    public PaginadoBet getPaginationBet() {
        return mPaginationBet;
    }

    public boolean isFirstTimeLoading() {
        return mIsFirstTimeLoading;
    }
}
