% legend - helvetica 12
% numbers - helvetics 16
% lines 4pt

set(gcf, 'PaperUnits', 'inches')
papersize = get(gcf, 'PaperSize')
% width = 5
% height = 5
% print -dtiff myfig.tiff

%width = 5
%height = 5

width = 20
height = 3

%width = 5;
%height = 4;

%width = 4.5;
%height = 2.54;

left = (papersize(1) - width)/2
bottom = (papersize(2) - height)/2

myfiguresize = [left, bottom, width, height]
set(gcf, 'PaperPosition', myfiguresize)
print -depsc myfig.eps
%print -dpng myfig.png