FROM tomcat:latest
MAINTAINER 10097454

# language setting
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
ENV LC_ALL "zh_CN.UTF-8"

WORKDIR /sping-boot-pay
ADD jdevtemplateboot.jar app.jar

RUN /bin/cp  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/spring-boot-pay.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

