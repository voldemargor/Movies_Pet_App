package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetGenresUseCase;

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
public final class GetGenresUseCase_Factory implements Factory<GetGenresUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetGenresUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetGenresUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetGenresUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new GetGenresUseCase_Factory(repositoryProvider);
  }

  public static GetGenresUseCase newInstance(Repository repository) {
    return new GetGenresUseCase(repository);
  }
}
