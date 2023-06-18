package com.example.moviespetapp.domain;

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
public final class RemoveBookmarkUseCase_Factory implements Factory<RemoveBookmarkUseCase> {
  private final Provider<Repository> repositoryProvider;

  public RemoveBookmarkUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RemoveBookmarkUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RemoveBookmarkUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new RemoveBookmarkUseCase_Factory(repositoryProvider);
  }

  public static RemoveBookmarkUseCase newInstance(Repository repository) {
    return new RemoveBookmarkUseCase(repository);
  }
}
