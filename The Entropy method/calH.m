function h=calH(f)

tem=zeros(size(f));
for i=1:size(f,1)
    for j=1:size(f,2)
        if f(i,j)==0
            tem(i,j)=0;
        else
            tem(i,j)=f(i,j)*log(f(i,j));
        end
    end
end  
h=-1/log(2)*sum(tem,2);