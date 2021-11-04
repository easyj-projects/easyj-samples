git checkout -B master origin/master
git merge github/master
git push

git checkout -B spring-boot-2.1.5.RELEASE github/spring-boot-2.1.5.RELEASE
git merge github/master

git checkout -B spring-boot-2.1.x github/spring-boot-2.1.x
git merge github/master

git checkout -B spring-boot-2.3.x github/spring-boot-2.3.x
git merge github/master

git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all
git push github --all