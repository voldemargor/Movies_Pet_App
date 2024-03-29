package com.example.moviespetapp.presentation.moviedetails;

import com.example.moviespetapp.data.sharedprefs.BookmarkService;
import com.example.moviespetapp.domain.usecase.AddBookmarkUseCase;
import com.example.moviespetapp.domain.usecase.GetMovieDetailsUseCase;
import com.example.moviespetapp.domain.usecase.RemoveBookmarkUseCase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MovieDetailsViewModel_MembersInjector implements MembersInjector<MovieDetailsViewModel> {
  private final Provider<BookmarkService> bookmarkServiceProvider;

  private final Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider;

  private final Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider;

  private final Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider;

  public MovieDetailsViewModel_MembersInjector(Provider<BookmarkService> bookmarkServiceProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider,
      Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider) {
    this.bookmarkServiceProvider = bookmarkServiceProvider;
    this.getMovieDetailsUseCaseProvider = getMovieDetailsUseCaseProvider;
    this.addBookmarkUseCaseProvider = addBookmarkUseCaseProvider;
    this.removeBookmarkUseCaseProvider = removeBookmarkUseCaseProvider;
  }

  public static MembersInjector<MovieDetailsViewModel> create(
      Provider<BookmarkService> bookmarkServiceProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<AddBookmarkUseCase> addBookmarkUseCaseProvider,
      Provider<RemoveBookmarkUseCase> removeBookmarkUseCaseProvider) {
    return new MovieDetailsViewModel_MembersInjector(bookmarkServiceProvider, getMovieDetailsUseCaseProvider, addBookmarkUseCaseProvider, removeBookmarkUseCaseProvider);
  }

  @Override
  public void injectMembers(MovieDetailsViewModel instance) {
    injectBookmarkService(instance, bookmarkServiceProvider.get());
    injectGetMovieDetailsUseCase(instance, getMovieDetailsUseCaseProvider.get());
    injectAddBookmarkUseCase(instance, addBookmarkUseCaseProvider.get());
    injectRemoveBookmarkUseCase(instance, removeBookmarkUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.moviedetails.MovieDetailsViewModel.bookmarkService")
  public static void injectBookmarkService(MovieDetailsViewModel instance,
      BookmarkService bookmarkService) {
    instance.bookmarkService = bookmarkService;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.moviedetails.MovieDetailsViewModel.getMovieDetailsUseCase")
  public static void injectGetMovieDetailsUseCase(MovieDetailsViewModel instance,
      GetMovieDetailsUseCase getMovieDetailsUseCase) {
    instance.getMovieDetailsUseCase = getMovieDetailsUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.moviedetails.MovieDetailsViewModel.addBookmarkUseCase")
  public static void injectAddBookmarkUseCase(MovieDetailsViewModel instance,
      AddBookmarkUseCase addBookmarkUseCase) {
    instance.addBookmarkUseCase = addBookmarkUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.moviedetails.MovieDetailsViewModel.removeBookmarkUseCase")
  public static void injectRemoveBookmarkUseCase(MovieDetailsViewModel instance,
      RemoveBookmarkUseCase removeBookmarkUseCase) {
    instance.removeBookmarkUseCase = removeBookmarkUseCase;
  }
}
