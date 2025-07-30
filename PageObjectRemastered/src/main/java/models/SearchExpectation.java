package models;

import java.util.List;

public class SearchExpectation {
    private final String searchKeyword;
    private final String expectedInTitle;
    private final List<String> notExpectedInTitle;

    private SearchExpectation(Builder builder) {
        this.searchKeyword = builder.searchKeyword;
        this.expectedInTitle = builder.expectedInTitle;
        this.notExpectedInTitle = builder.notExpectedInTitle;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getExpectedInTitle() {
        return expectedInTitle;
    }

    public List<String> getNotExpectedInTitle() {
        return notExpectedInTitle;
    }

    public static class Builder {
        private String searchKeyword;
        private String expectedInTitle;
        private List<String> notExpectedInTitle;

        public Builder searchKeyword(String keyword) {
            this.searchKeyword = keyword;
            return this;
        }

        public Builder expectedInTitle(String expected) {
            this.expectedInTitle = expected;
            return this;
        }

        public Builder notExpectedInTitle(List<String> unexpected) {
            this.notExpectedInTitle = unexpected;
            return this;
        }

        public SearchExpectation build() {
            return new SearchExpectation(this);
        }
    }
}