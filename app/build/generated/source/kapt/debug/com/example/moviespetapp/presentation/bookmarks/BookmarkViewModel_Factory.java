package com.example.moviespetapp.presentation.bookmarks;

import com.example.moviespetapp.domain.usecase.GetFavMoviesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BookmarkViewModel_Factory implements Factory<BookmarkViewModel> {
  private final Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider;

  public BookmarkViewModel_Factory(Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider) {
    this.getFavMoviesUseCaseProvider = getFavMoviesUseCaseProvider;
  }

  @Override
  public BookmarkViewModel get() {
    BookmarkViewModel instance = newInstance();
    BookmarkViewModel_MembersInjector.injectGetFavMoviesUseCase(instance, getFavMoviesUseCaseProvider.get());
    return instance;
  }

  public static BookmarkViewModel_Factory create(
      Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider) {
    return new BookmarkViewModel_Factory(getFavMoviesUseCaseProvider);
  }

  public static BookmarkViewModel newInstance() {
    return new BookmarkViewModel();
  }
}
