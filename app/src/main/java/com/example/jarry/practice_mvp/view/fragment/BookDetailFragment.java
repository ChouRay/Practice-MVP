package com.example.jarry.practice_mvp.view.fragment;

import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jarry.practice_mvp.R;
import com.example.jarry.practice_mvp.model.BookEntity;
import com.example.jarry.practice_mvp.presenter.BookDetailsPresenter;
import com.example.jarry.practice_mvp.view.LoadDataView;

public class BookDetailFragment extends Fragment implements LoadDataView{

    public static final String ISBN = "9787121060748";
    private BookDetailsPresenter presenter;

    private ImageView imageViewBook;
    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewPublisher;
    private TextView textViewPubdate;
    private TextView textViewSummary;
    private TextView textViewAuthorIntro;
    private TextView textViewCatalog;
    private RelativeLayout rlProgress;



    public static BookDetailFragment newInstance() {
        return new BookDetailFragment();
    }

    public BookDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWidget();
        presenter = new BookDetailsPresenter(getActivity(), ISBN);
        presenter.setView(this);
        presenter.loadData();
    }

    private void initWidget() {
        imageViewBook = (ImageView) getActivity().findViewById(R.id.imageView_book);
        textViewTitle = (TextView) getActivity().findViewById(R.id.textView_title);
        textViewAuthor = (TextView) getActivity().findViewById(R.id.textView_author);
        textViewPublisher = (TextView) getActivity().findViewById(R.id.textView_publisher);
        textViewPubdate = (TextView) getActivity().findViewById(R.id.textView_pubdate);
        textViewSummary = (TextView) getActivity().findViewById(R.id.textView_summary);
        textViewAuthorIntro = (TextView) getActivity().findViewById(R.id.textView_author_intro);
        textViewCatalog = (TextView) getActivity().findViewById(R.id.textView_catalog);
        rlProgress = (RelativeLayout) getActivity().findViewById(R.id.rl_progress);
    }

    @Override
    public void showDetailsView(BookEntity entity) {

        this.textViewTitle.setText(entity.getTitle());
        String author = "";
        for (int j=0; j<entity.getAuthor().size(); j++) {
            author += entity.getAuthor().get(j) + " ";
        }
        this.textViewAuthor.setText(author);
        this.textViewPublisher.setText(entity.getPublisher());
        this.textViewPubdate.setText(entity.getPubdate());
        this.textViewSummary.setText(entity.getSummary());
        this.textViewAuthorIntro.setText(entity.getAuthor_intro());
        this.textViewCatalog.setText(entity.getCatalog());
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
