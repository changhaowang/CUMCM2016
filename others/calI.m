function I=calI(A,B)
I=(1/length(A).^2)*sum(sum(abs(A+B-1)));