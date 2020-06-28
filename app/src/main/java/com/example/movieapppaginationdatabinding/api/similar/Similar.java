package com.example.movieapppaginationdatabinding.api.similar;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Similar extends BaseObservable {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<SimilarResult> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @Bindable
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        notifyPropertyChanged(BR.page);
    }
    @Bindable
    public List<SimilarResult> getResults() {
        return results;
    }

    public void setResults(List<SimilarResult> results) {
        this.results = results;
        notifyPropertyChanged(BR.results);
    }
    @Bindable
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        notifyPropertyChanged(BR.totalPages);
    }
    @Bindable
    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        notifyPropertyChanged(BR.totalResults);
    }
}