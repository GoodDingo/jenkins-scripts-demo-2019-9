// ulozit toto do souboru zakaz_joby.groovy

import com.cloudbees.hudson.plugins.folder.AbstractFolder
import hudson.model.AbstractProject
import jenkins.model.Jenkins

// closure that will disable jobs
def disableJobs = { println "Disabling '${it.fullName}'"; it.disable() }

//Function to retrieve all buildable Project in a specific Folder
def doAllItemsInFolder(folderName, closure) {
    AbstractFolder folder = Jenkins.instance.getAllItems(AbstractFolder.class)
            .find {folder -> folderName == folder.fullName };

    folder.getAllJobs()
            .findAll {job -> job instanceof AbstractProject}
            .findAll {job -> job.isBuildable()}
            .each {closure(it)};
}


FOLDER = args[0]
if (!(FOLDER ==~ /^(?:(?:TEAMS)|(?:SEED_JOBS))\/\S+/ )) {
    result = "ERROR: FOLDER must point into a folder inside TEAMS or SEED_JOBS folder."
    println result
    return result
}

doAllItemsInFolder(FOLDER, disableJobs);