function example=findclose(centroids, idx, X)
close=zeros(1,length(centroids));
costidx=zeros(1,length(centroids));
for i=1: size(centroids,1)
    costidx(i)=10000000000;
end

for i=1:size(centroids,1)
for j=1:size(X,1)
    if (idx(j)==i)&&costidx(i)>cost(centroids(i),X(j)),
        close(i)=j;
        costidx(i)=cost(centroids(i),X(j));
    end
end
end
for i=1:size(centroids,1)
example(i,:)=X(close(i),:);
end


        
        