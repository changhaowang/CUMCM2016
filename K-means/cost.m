function co=cost(A,B)
co=0;
for i=1:length(A)
    co=co+(A(i)-B(i)).^2;
end