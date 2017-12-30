function r  = calR(a)
N=size(a,2);
max=zeros(5,1);
min=zeros(5,1);

for i=1:5
    if a(i,1)>a(i,2)
        max(i)=a(i,1);
        min(i)=a(i,2);
    else
        max(i)=a(i,2);
        min(i)=a(i,1);
    end
end
r(1,:)=1-a(1,:)./(sum(a(1,:)));
r(2,:)=1-a(2,:)./(sum(a(2,:)));
r(3,:)=1-a(3,:)./(sum(a(3,:)));
r(4,:)=1-a(4,:)./(sum(a(4,:)));
r(5,:)=a(5,:)./(sum(a(5,:)));

