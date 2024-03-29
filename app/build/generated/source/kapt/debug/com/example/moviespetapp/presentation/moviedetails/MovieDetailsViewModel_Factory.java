package com.example.moviespetapp.presentation.moviedetails;

import com.example.moviespetapp.data.sharedprefs.BookmarkService;
import com.example.moviespetapp.domain.usecase.AddBookmarkUseCase;
import com.example.moviespetapp.domain.usecase.GetMovieDetailsUseCase;
import com.example.moviespetapp.domain.usecase.RemoveBookmarkUseCase;
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
public final class MovieDetailsViewModel_Factory implements Factory<MovieDetailsViewModel> {
  private final Provider<BookmarkService> bookmarkServiceProvider;

  private final Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider;

  private final Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider;

  private final Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider;

  public MovieDetailsViewModel_Factory(Provider<BookmarkService> bookmarkServiceProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider,
      Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider) {
    this.bookmarkServiceProvider = bookmarkServiceProvider;
    this.getMovieDetailsUseCaseProvider = getMovieDetailsUseCaseProvider;
    this.addBookmarkUseCaseProvider = addBookmarkUseCaseProvider;
    this.removeBookmarkUseCaseProvider = removeBookmarkUseCaseProvider;
  }

  @Override
  public MovieDetailsViewModel get() {
    MovieDetailsViewModel instance = newInstance();
    MovieDetailsViewModel_MembersInjector.injectBookmarkService(instance, bookmarkServiceProvider.get());
    MovieDetailsViewModel_MembersInjector.injectGetMovieDetailsUseCase(instance, getMovieDetailsUseCaseProvider.get());
    MovieDetailsViewModel_MembersInjector.injectAddBookmarkUseCase(instance, addBookmarkUseCaseProvider.get());
    MovieDetailsViewModel_MembersInjector.injectRemoveBookmarkUseCase(instance, removeBookmarkUseCaseProvider.get());
    return instance;
  }

  public static MovieDetailsViewModel_Factory create(
      Provider<BookmarkService> bookmarkServiceProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider,
      Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider) {
    return new MovieDetailsViewModel_Factory(bookmarkServiceProvider, getMovieDetailsUseCaseProvider, addBookmarkUseCaseProvider, removeBookmarkUseCaseProvider);
  }

  public static MovieDetailsViewModel newInstance() {
    return new MovieDetailsViewModel();
  }
}
