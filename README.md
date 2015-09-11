# Practice-MVP
a practice of  MVP Design Pattern

前段时间做项目时有用到一个比较常用的设计模式-MVP模式，我个人比较喜欢有优美的结构的代码，我始终相信代码也是一种艺术，现在就特地将这一部分拿出来分享哈，自己也可以再熟悉熟悉。
OK，废话不多说先上个图，来看看我的代码中MVP模式的应用：
![mvpimg](http://7xl9ah.com1.z0.glb.clouddn.com/blogIMG_20150911_202523.JPG-blog)

 - **View** 代表的是Android中对应的Activity，Fragment或View等与UI相关的。（view通常含有Presenter的引用）
 - **ViewInterface** 用来提取一些可重用的功能，比如Pregress等以及方便的更改View，类之间更加的模块化。使用时，只需要让**View**去实现这个接口.
 - **Presenter** 相当于一个路由，又来自View的转发请求，以及Model回调的数据实体。（Presenter持有Model层的应用，以及ViewInterface的引用）
 - **Model** 业务逻辑层。提取数据从Database，或云端API，缓存等，然后再对数据加以处理，再分发给Presenter去处理。
 
 OK，来个栗子看看。一个根据图书的isbn，从豆瓣的RestFul API中提取图书的细节信息，再返回给前台界面。

####1.在View层中
```java
/**
 * ViewInterface
 * 定义一些通用的view接口
 */
public interface LoadDataView {
	/***
	 * 耗时操作，加载数据，显示Progress
	 */
    void showLoading();
    /***
     * 隐藏Progress
     */
    void hideLoading();
}
```

```java
/**
 * 更细小的，用来显示图书细节的View接口
 */
public interface LoadBookView extends LoadDataView {
    void showDetailsView(BookEntity entity);
}
```

```java

/***
 * Fragment,属于View层，实现了ViewInterface（LoadBookView）
 */
public class BookDetailFragment extends Fragment implements LoadBookView{
	/**图书条形码ISBN号*/
    public static final String ISBN = "9787121060748";
    /**持Presenter对象*/
    private BookDetailsPresenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWidget();
        presenter = new BookDetailsPresenter(getActivity(), ISBN); // 实例化一个presenter对象
        presenter.setView(this); //让Presenter持一个ViewInterface实例（LoadBookView）
        presenter.loadData(); //告诉Presenter快给我加载Data
    }    

     @Override
    public void showDetailsView(BookEntity entity) {
	    //更新UI等操作
    }

    @Override
    public void showLoading() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rlProgress.setVisibility(View.GONE);
    }
}
```

#### 2.在Presenter层中
```java
public class BookDetailsPresenter {
	/**持一个Model层的对象，用来从网页接口Rest Api中提取数据*/
    private RestApi restApi = null;
    /**一个ViewInterface对象，用来回调Data给View*/
    private LoadBookView loadBookView;
    private String isbn;

    public BookDetailsPresenter(Context context, String isbn) {
        restApi = new RestApiImpl(context);
        this.isbn =isbn;
    }
    public void setView(LoadBookView loadBookView) {
        this.loadBookView = loadBookView;
    }
    public void loadData() {
        loadBookView.showLoading();
        //耗时操作，开个线程异步的加载数据
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                restApi.getBookDetailByIsbn(isbn, bookDetailsCallback);
            }
        });
        thread.start();
    }
    //匿名内部类，接收bookDetailCallback的回调数据
    private RestApi.BookDetailsCallback bookDetailsCallback = new RestApi.BookDetailsCallback() {
        @Override
        public void onBookEntityLoaded(BookEntity bookEntity) {
            notifyDataLoadedSuccessful(bookEntity);
            BookDetailsPresenter.this.loadBookView.hideLoading();
        }

        @Override
        public void onError(Exception e) {
			//异常后的相关处理
        }
    };

	/***
	* 通知获取数据成功了，赶快通知UI更新吧
	*/
    private void notifyDataLoadedSuccessful(final BookEntity bookEntity) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                BookDetailsPresenter.this.loadBookView.showDetailsView(bookEntity);
            }
        });
    }  
}
```

#### 3.在Model层中

```java
/***
* 整个应用程序需要的数据实体类
*/
public class BookEntity {
	//一些set，get方法
}

/**
 * 一个接口，用来从rest api api获得数据，它的实现在RestApiImpl中
 */
public interface RestApi {
    String API_ISBN_BASE_URL = "https://api.douban.com/v2/book/isbn/";
	/***
	* 更细小的接口，用来将获取到的数据，回调给它的调用者
	*/
    interface BookDetailsCallback {
        void onBookEntityLoaded(BookEntity bookEntity);
        void onError(Exception e);
    }
    /**
     * 从网络获取数据，然后通过bookDetailCallback回调给Presenter
     * @param isbn
     * @param bookDetailsCallback
     */
    void getBookDetailByIsbn(final String isbn, final BookDetailsCallback bookDetailsCallback);

}
```
好了核心的代码就贴到这儿了，每个类关键的地方都偶注释，整个Demo的代码在[github](https://github.com/ChouRay/Practice-MVP)上，有兴趣可以下来看看。有不好的地方也多多指正，感谢万分！





