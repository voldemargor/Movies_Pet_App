package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.AddBookmarkUseCase;

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
public final class AddBookmarkUseCase_Factory implements Factory<AddBookmarkUseCase> {
  private final Provider<Repository> repositoryProvider;

  public AddBookmarkUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddBookmarkUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddBookmarkUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new AddBookmarkUseCase_Factory(repositoryProvider);
  }

  public static AddBookmarkUseCase newInstance(Repository repository) {
    return new AddBookmarkUseCase(repository);
  }
}
