package com.github.il4enkodev.househeating.domain.interactor;

public interface UseCase<R, A> {

    R execute(A arguments);

}
