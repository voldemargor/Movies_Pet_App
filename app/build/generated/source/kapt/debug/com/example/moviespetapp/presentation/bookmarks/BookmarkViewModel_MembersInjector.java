package com.example.moviespetapp.presentation.bookmarks;

import com.example.moviespetapp.domain.usecase.GetFavMoviesUseCase;
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
public final class BookmarkViewModel_MembersInjector implements MembersInjector<BookmarkViewModel> {
  private final Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider;

  public BookmarkViewModel_MembersInjector(
      Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider) {
    this.getFavMoviesUseCaseProvider = getFavMoviesUseCaseProvider;
  }

  public static MembersInjector<BookmarkViewModel> create(
      Provider<GetFavMoviesUseCase> getFavMoviesUseCaseProvider) {
    return new BookmarkViewModel_MembersInjector(getFavMoviesUseCaseProvider);
  }

  @Override
  public void injectMembers(BookmarkViewModel instance) {
    injectGetFavMoviesUseCase(instance, getFavMoviesUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.bookmarks.BookmarkViewModel.getFavMoviesUseCase")
  public static void injectGetFavMoviesUseCase(BookmarkViewModel instance,
      GetFavMoviesUseCase getFavMoviesUseCase) {
    instance.getFavMoviesUseCase = getFavMoviesUseCase;
  }
}
